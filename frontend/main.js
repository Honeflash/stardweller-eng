const copyButton = document.getElementById('copy-text');
copyButton.addEventListener("click", function toClipboard() {
    try {
            navigator.clipboard.writeText(document.getElementById("str-area").innerText);
        } catch(error){
            console.error("Failed to copy.", error);
        }
});

document.getElementById('year').innerText = new Date().getFullYear();