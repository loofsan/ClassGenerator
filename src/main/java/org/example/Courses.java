package org.example;

import java.util.Scanner;

public class Courses {

    public enum Course {
        ACCOUNTING(1, "ACTG"),
        ADMINISTRATION_OF_JUSTICE(2, "ADMJ"),
        AMERICAN_SIGN_LANGUAGE(3, "ASL"),
        ANESTHESIA_TECHNOLOGY(4, "ANST"),
        ANTHROPOLOGY(5, "ANTH"),
        ARABIC(6, "ARBC"),
        ART(7, "ART"),
        ASTRONOMY(8, "ASTR"),
        AUTOMOTIVE_TECHNOLOGY(9, "AUTO"),
        BARBERING(10, "BARB"),
        BIOLOGY(11, "BIOL"),
        BIOTECHNOLOGY(12, "BTEC"),
        BUSINESS(13, "BUS."),
        BUSINESS_COMPUTER_SYSTEMS_AND_MANAGEMENT(14, "BCM."),
        CAREER_AND_PERSONAL_DEVELOPMENT(15, "CRER"),
        CHEMISTRY(16, "CHEM"),
        CHINESE(17, "CHIN"),
        COMMUNICATION_STUDIES(18, "COMM"),
        COMPUTER_INFORMATION_SYSTEMS_APPLICATIONS(19, "CISA"),
        COMPUTER_SCIENCE(20, "COMP"),
        COOPERATIVE_EDUCATION(21, "COOP"),
        COSMETOLOGY(22, "COSM"),
        COUNSELING(23, "COUN"),
        DIGITAL_MEDIA_AND_DESIGN(24, "DMAD"),
        DRAMA(25, "DRAM"),
        ECONOMICS(26, "ECON"),
        EDUCATION_CHILD_DEVELOPMENT(27, "ECE."),
        EDUCATION(28, "EDUC"),
        EDUCATIONAL_ACCESS(29, "EDAC"),
        ELECTRONICS_TECHNOLOGY(30, "ELEC"),
        EMERGENCY_MEDICAL_CARE(31, "EMC."),
        ENERGY_SYSTEMS_TECHNOLOGY_MANAGEMENT(32, "ESTM"),
        ENGINEERING(33, "ENGR"),
        ENGLISH(34, "ENGL"),
        ENGLISH_FOR_SPEAKERS_OF_OTHER_LANGUAGES(35, "ESOL"),
        ENVIRONMENTAL_SCIENCE_AND_TECHNOLOGY(36, "ENVS"),
        ESTHETICS(37, "ESTI"),
        ETHNIC_STUDIES(38, "ETHN"),
        FILIPINO(39, "FILI"),
        FILM(40, "FILM"),
        GEOGRAPHY(41, "GEOG"),
        GEOLOGY(42, "GEOL"),
        HEALTH_SCIENCE(43, "HSCI"),
        HISTORY(44, "HIST"),
        HOSPITALITY_AND_TOURISM_MANAGEMENT(45, "HTM."),
        INTERDISCIPLINARY_STUDIES(46, "IDST"),
        INTERNATIONAL_BUSINESS(47, "IBUS"),
        JOURNALISM(48, "JOUR"),
        KINESIOLOGY_ADAPTIVE_PHYSICAL_EDUCATION(49, "ADAP"),
        KINESIOLOGY_COMBATIVES(50, "COMB"),
        KINESIOLOGY_DANCE(51, "DANC"),
        KINESIOLOGY_FITNESS(52, "FITN"),
        KINESIOLOGY_INDIVIDUAL_SPORTS(53, "INDV"),
        KINESIOLOGY(54, "KINE"),
        KINESIOLOGY_PHYSICAL_EDUCATION(55, "P.E."),
        KINESIOLOGY_TEAM_SPORTS(56, "TEAM"),
        KINESIOLOGY_VARSITY_SPORTS(57, "VARS"),
        LEARNING_SKILLS(58, "LSKL"),
        LEGAL_STUDIES(59, "LGST"),
        LITERATURE(60, "LIT."),
        MANAGEMENT(61, "MGMT"),
        MATHEMATICS(62, "MATH"),
        MEDICAL_ASSISTING(63, "MEDA"),
        MUSIC(64, "MUS."),
        NETWORK_ENGINEERING_TECHNOLOGIES(65, "NETX"),
        OCEANOGRAPHY(66, "OCEN"),
        PARALEGAL_STUDIES(67, "LEGL"),
        PHILOSOPHY(68, "PHIL"),
        PHYSICS(69, "PHYS"),
        POLITICAL_SCIENCE(70, "PLSC"),
        PSYCHOLOGY(71, "PSYC"),
        REAL_ESTATE(72, "R.E."),
        RESPIRATORY_CARE(73, "RPTH"),
        SOCIAL_JUSTICE_STUDIES(74, "SJS"),
        SOCIAL_SCIENCE(75, "SOSC"),
        SOCIOLOGY(76, "SOCI"),
        SPANISH(77, "SPAN"),
        SURGICAL_CAREERS(78, "SURG"),
        WELLNESS(79, "WELL");

        private final int code;
        private final String abbreviation;

        Course(int code, String abbreviation) {
            this.code = code;
            this.abbreviation = abbreviation;
        }

        public int getCode() {
            return code;
        }

        public String getAbbreviation() {
            return abbreviation;
        }
    }
}
