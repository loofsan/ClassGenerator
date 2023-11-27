// This code is for converting the strings from the JSWebScraper Json to their
// respective ints and floats.
const result = data.map(o => Object.entries(o).reduce((acc, [key, value]) => {
    if (key === 'NumOfRating') {
        // Remove " ratings " and convert the remaining to an integer
        acc[key] = parseInt(value.replace('rating', ''), 10);
    } else if (['Difficult', 'Rating', 'Difficulty'].includes(key)) {
        // Convert the value to float
        acc[key] = parseFloat(value);
    } else {
        acc[key] = value; // Keep other keys unchanged
    }
    return acc;
}, {}));
