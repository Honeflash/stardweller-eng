// -------------------------------------------- Handles using the copy button on the webpage --------------------------------------------
const copyButton = document.getElementById('copy-text');            // Path the document element to a variable
const emptyBox = document.getElementById("empty-msg");              // document element for display a message when pressing the copy button

copyButton.addEventListener("click", function() {
    if(document.getElementById("str-area").value.length == 0) {     // No text is available to copy
        // Display a message to inform the user that there's nothing to copy
        emptyBox.style.color = "red"
        emptyBox.innerText = "There is nothing to copy!";
    } else {                                                        // Text is available to copy
        try {
            navigator.clipboard.writeText(document.getElementById("str-area").value);

            // Display a message to inform the user that the text was successfully copied
            emptyBox.style.color = "green"
            emptyBox.innerText = "Copied!";
        } catch(error) {                                            // An error occurred while trying to copy
            console.error("Failed to copy.", error);
        }
    }
});
// -------------------------------------------- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX --------------------------------------------

// -------------------------------------------- Handles using the translate button on the webpage --------------------------------------------
const translateButton = document.getElementById('send-query');
const url = "https://honeflash.github.io/stardweller-eng/translator";

translateButton.addEventListener("click", function() {              // Basic testing function for managing api calls.
    let baseData = document.getElementById('eng-area').value;       // Gets the current value from the textarea to send in a fetch request

    fetch(`${url}?initString=${encodeURIComponent(baseData)}`)
        .then(response => response.text())
        .then(data => {                                             // For testing, data is sent to the console, and displayed in the second textarea
        console.log(data)
        document.getElementById('str-area').value = data
        })
        .catch(error => console.log(error));                        // Catches any errors that may occur during the fetch request
    emptyBox.innerText = "";
});
// -------------------------------------------- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX --------------------------------------------


document.getElementById('year').innerText = new Date().getFullYear(); // Gets and displays the current year
