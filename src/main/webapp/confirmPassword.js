function validatePassword() {
    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirmPassword").value;

    var passwordError = document.getElementById("passwordError");

    if (password !== confirmPassword) {
        passwordError.textContent = "Passwords do not match";
    } else {
        passwordError.textContent = "";
    }
}
function validateForm() {
    var passwordError = document.getElementById("passwordError").textContent;
    if (passwordError) {
        // Passwords do not match, prevent form submission
        return false;
    }
    return true; // Allow form submission
}