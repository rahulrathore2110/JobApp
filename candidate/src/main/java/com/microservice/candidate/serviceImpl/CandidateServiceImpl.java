package com.microservice.candidate.serviceImpl;

import com.microservice.candidate.Exception.ResourceNotFound;
import com.microservice.candidate.model.*;
import com.microservice.candidate.repository.CandidateDao;
import com.microservice.candidate.repository.CertificationDao;
import com.microservice.candidate.repository.EducationDao;
import com.microservice.candidate.repository.ExperienceDao;
import com.microservice.candidate.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateDao candidateDao;

     @Autowired
    private CertificationDao certificationDao;

    @Autowired
    private EducationDao educationDao;

    @Autowired
    private ExperienceDao experienceDao;


    @Override
    public Candidate createCandidate(Candidate candidate) {
        Candidate candidate1 = this.candidateDao.save(candidate);
        return candidate1 ;
    }

    @Override
    public Candidate updateCandidate(Candidate candidate,Integer id) {
        Candidate candidate1 = this.candidateDao.findById(id).orElseThrow(() -> new ResourceNotFound("No Candidate found with this id : " + id));
        candidate1.setName(candidate.getName());
        candidate1.setEmail(candidate.getEmail());
        candidate1.setNumber(candidate.getNumber());

        return this.candidateDao.save(candidate1);
    }

    @Override
    public Candidate createCandidatePersonalDetails(PersonalDetails personalDetails, Integer id) {
        Candidate candidate1 = this.candidateDao.findById(id).orElseThrow(() -> new ResourceNotFound("No Candidate found with this id : " + id));
        candidate1.setPersonalDetails(personalDetails);
        personalDetails.setCandidate(candidate1);
        return this.candidateDao.save(candidate1);
    }

    @Override
    public Candidate createCandidateEducation(Education education, Integer id) {
        Candidate candidate1 = this.candidateDao.findById(id).orElseThrow(() -> new ResourceNotFound("No Candidate found with this id : " + id));
        List<Education> educations = candidate1.getEducations();
        educations.add(education);
        candidate1.setEducations(educations);
        education.setCandidate(candidate1);
        return this.candidateDao.save(candidate1);
    }

    @Override
    public Candidate createCandidateExperience(Experience experience, Integer id) {
        Candidate candidate1 = this.candidateDao.findById(id).orElseThrow(() -> new ResourceNotFound("No Candidate found with this id : " + id));
        List<Experience> experiences = candidate1.getExperiences();
        experiences.add(experience);
        candidate1.setExperiences(experiences);
        experience.setCandidate(candidate1);
        return this.candidateDao.save(candidate1);
    }

    @Override
    public Candidate createCandidateCertification(Certification certification, Integer id) {
        Candidate candidate1 = this.candidateDao.findById(id).orElseThrow(() -> new ResourceNotFound("No Candidate found with this id : " + id));
        List<Certification> certifications = candidate1.getCertifications();
        certifications.add(certification);
        candidate1.setCertifications(certifications);
        certification.setCandidate(candidate1);
        return this.candidateDao.save(candidate1);
    }

    @Override
    public Candidate updateCandidateEducation(Education education) {
        Education education1 = this.educationDao.findById(education.getEduId()).orElseThrow(() -> new ResourceNotFound("No Education Details Found with this is : " + education.getEduId()));
        education.setCandidate(education1.getCandidate());

        Candidate candidate = education1.getCandidate();

        List<Education> educations =  candidate.getEducations();
        educations.remove(education1);
        educations.add(education);

        candidate.setEducations(educations);

        return this.candidateDao.save(candidate);
    }

    @Override
    public Candidate updateCandidateExperience(Experience experience) {
        Experience experience1 = this.experienceDao.findById(experience.getEid()).orElseThrow(()->new ResourceNotFound("No experience found with this id : "+ experience.getEid()));
        experience.setCandidate(experience1.getCandidate());

        Candidate candidate = experience1.getCandidate();

        List<Experience>  experiences = candidate.getExperiences();
        experiences.remove(experience1);
        experiences.add(experience);

        candidate.setExperiences(experiences);

        return this.candidateDao.save(candidate);
    }

    @Override
    public Candidate updateCandidateCertification(Certification certification) {
        Certification certification1 = this.certificationDao.findById(certification.getCid()).orElseThrow(() -> new ResourceNotFound("No certificate found with this Id : " + certification.getCid()));
        certification.setCandidate(certification1.getCandidate());

        Candidate candidate = certification1.getCandidate();

        List<Certification>  certifications = candidate.getCertifications();
        certifications.remove(certification1);
        certifications.add(certification);

        candidate.setCertifications(certifications);

        return this.candidateDao.save(candidate);
    }

    @Override
    public Candidate deleteCandidateEducation(Integer id) {
        Education education1 = this.educationDao.findById(id).orElseThrow(() -> new ResourceNotFound("No Education Details Found with this is : " + id));

        Candidate candidate = education1.getCandidate();

        List<Education> educations =  candidate.getEducations();
        educations.remove(education1);

        candidate.setEducations(educations);

        this.educationDao.delete(education1);
        return this.candidateDao.save(candidate);
    }

    @Override
    public Candidate deleteCandidateExperience(Integer id) {
        Experience experience1 = this.experienceDao.findById(id).orElseThrow(()->new ResourceNotFound("No experience found with this id : "+ id));

        Candidate candidate = experience1.getCandidate();

        List<Experience>  experiences = candidate.getExperiences();
        experiences.remove(experience1);

        candidate.setExperiences(experiences);

        this.experienceDao.delete(experience1);
        return this.candidateDao.save(candidate);
    }

    @Override
    public Candidate deleteCandidateCertification(Integer id) {
        Certification certification1 = this.certificationDao.findById(id).orElseThrow(() -> new ResourceNotFound("No certificate found with this Id : " + id));

        Candidate candidate = certification1.getCandidate();

        List<Certification>  certifications = candidate.getCertifications();
        certifications.remove(certification1);

        candidate.setCertifications(certifications);

        this.certificationDao.delete(certification1);
        return this.candidateDao.save(candidate);
    }

    @Override
    public Candidate addLanguages(String language, Integer id) {
        Candidate candidate = this.candidateDao.findById(id).orElseThrow(() -> new ResourceNotFound("No candidate found with this id : " + id));
        HashSet<String> languages = candidate.getLanguages();
        languages.add(language);

        candidate.setLanguages(languages);

        return this.candidateDao.save(candidate);
    }

    @Override
    public Candidate addSkills(String skill, Integer id) {
        Candidate candidate = this.candidateDao.findById(id).orElseThrow(() -> new ResourceNotFound("No candidate found with this id : " + id));
        HashSet<String> skills = candidate.getSkills();
        skills.add(skill);

        candidate.setSkills(skills);

        return this.candidateDao.save(candidate);
    }

    @Override
    public Candidate deleteLanguages(String language, Integer id) {
        Candidate candidate = this.candidateDao.findById(id).orElseThrow(() -> new ResourceNotFound("No candidate found with this id : " + id));
        HashSet<String> languages = candidate.getLanguages();

        if (languages.isEmpty()){
            throw new ResourceNotFound("No language found to delete");
        }
        languages.remove(language);

        candidate.setLanguages(languages);

        return this.candidateDao.save(candidate);
    }

    @Override
    public Candidate deleteSkills(String skill, Integer id) {
        Candidate candidate = this.candidateDao.findById(id).orElseThrow(() -> new ResourceNotFound("No candidate found with this id : " + id));
        HashSet<String> skills = candidate.getSkills();

        if (skill.isEmpty()){
            throw new ResourceNotFound("No Skill found to delete");
        }
        skills.remove(skill);

        candidate.setSkills(skills);

        return this.candidateDao.save(candidate);
    }


    @Override
    public Candidate getCandidateById(Integer id) {
        Candidate candidate1 = this.candidateDao.findById(id).orElseThrow(() -> new ResourceNotFound("No Candidate found with this id : " + id));
        return candidate1;
    }

    @Override
    public List<Candidate> getAllCandidates() {
        List<Candidate> candidates = this.candidateDao.findAll();

        if (candidates.isEmpty()){
            throw new ResourceNotFound("No candidate found at this moment");
        }
        return candidates;
    }

    @Override
    public String applyForJob(Integer id, Integer jobId) {
        Candidate candidate1 = this.candidateDao.findById(id).orElseThrow(() -> new ResourceNotFound("No Candidate found with this id : " + id));
        Set<Integer> allApplyJobs = candidate1.getAllApplyJobs();
        if (allApplyJobs != null){
            allApplyJobs.add(jobId);
            candidate1.setAllApplyJobs(allApplyJobs);
        }else {
            candidate1.setAllApplyJobs(new HashSet<>(jobId));
        }

        this.candidateDao.save(candidate1);
        return "Job apply successfully";
    }
}
