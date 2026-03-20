function sendToDatabase() {
    //URL to server-side endpoints
}

// -------------------------------------------- Handles using the copy button on the webpage --------------------------------------------
const copyButton = document.getElementById('copy-text'); // Path the document element to a variable
const emptyBox = document.getElementById("empty-msg"); // document element for display a message when pressing the copy button

copyButton.addEventListener("click", function() {
    if(document.getElementById("str-area").innerText.length == 0) { // No text is available to copy
        // Display a message to inform the user that there's nothing to copy
        emptyBox.style.color = "red"
        emptyBox.innerText = "There is nothing to copy!";
    } else {                                                        // Text is available to copy
        try {
            navigator.clipboard.writeText(document.getElementById("str-area").innerText);

            // Display a message to inform the user that the text was successfully copied
            emptyBox.style.color = "green"
            emptyBox.innerText = "Copied!";
        } catch(error) {                                            // An error occurred while trying to copy
            console.error("Failed to copy.", error);
        }
    }
});
// -------------------------------------------- XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX --------------------------------------------

document.getElementById('year').innerText = new Date().getFullYear(); // Gets and displays the current year