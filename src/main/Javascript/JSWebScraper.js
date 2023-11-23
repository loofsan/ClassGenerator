//RateMyProfessor Console Scraper

//Professor Ids
let professorIds = [];
for(let i = 1; i < 586; i++) {
    professorIds.push(i);
}

// Map all the professor names into an array
let professorNameElements = document.querySelectorAll(".CardName__StyledCardName-sc-1gyrgim-0.cJdVEK");
let professorNameArray = Array.from(professorNameElements);

let professorNames = professorNameArray.map(element => element.textContent);

// Map all the professor ratings into an array
let ratingElements = document.querySelectorAll("[class^='CardNumRating__CardNumRatingNumber']");
let ratingArray = Array.from(ratingElements);

let professorRatings = ratingArray.map(element => element.textContent);

// Map all the professor's number of ratings into an array
let professorNumOfRatingElements = document.querySelectorAll("[class^='CardNumRating__CardNumRatingCount']");
let professorNumOfRatingArray = Array.from(professorNumOfRatingElements);

let professorNumOfRatings = professorNumOfRatingArray.map(element => element.textContent);

// Map all the professor's subjects into an array
let professorSubjectElements = document.querySelectorAll("[class^='CardSchool__Department']");
let professorSubjectArray = Array.from(professorSubjectElements);

let professorSubjects = professorSubjectArray.map(element => element.textContent);

// Map all the professor's difficulty into an array
let difficultyElements = document.querySelectorAll("[class^='CardFeedback__CardFeedbackNumber']");
let difficultyArray = Array.from(difficultyElements);

let everySecondDifficulty = difficultyArray.filter((element, index) => index % 2 === 1);
let professorDifficulty = everySecondDifficulty.map(element => element.textContent);

let professorDetails = professorIds.map((id, index_value) => {
    return {
        id: id,
        Professor: professorNames[index_value],
        Subject: professorSubjects[index_value],
        Rating: professorRatings[index_value],
        NumOfRating: professorNumOfRatings[index_value],
        Difficulty: professorDifficulty[index_value],
    };
});

copy(professorDetails);






