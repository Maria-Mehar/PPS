document.getElementById("registrationForm").addEventListener("submit", function(event) {
    let isValid = true;

    // Full Name Validation (Only letters and spaces allowed)
    const fullName = document.getElementById("fullName").value.trim();
    if (!/^[A-Za-z\s]+$/.test(fullName)) {
        alert("Only letters and spaces allowed.");
        isValid = false;
    }

    // Email Validation (Proper email format)
    const email = document.getElementById("email").value.trim();
    if (!/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(email)) {
        alert("Invalid email format.");
        isValid = false;
    }

    // Student ID Validation (Exactly 6 characters long)
    const studentId = document.getElementById("studentId").value.trim();
    if (studentId.length !== 6) {
        alert("Student ID must be 6 characters long.");
        isValid = false;
    }

    // Password Validation (8+ chars, 1 uppercase, 1 lowercase, 1 digit)
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirmPassword").value;

    if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/.test(password)) {
        alert("Password must contain at least 8 characters, including 1 uppercase letter, 1 lowercase letter, and 1 digit.");
        isValid = false;
    }

    // Confirm Password Validation (Must match password)
    if (confirmPassword !== password) {
        alert("Passwords do not match.");
        isValid = false;
    }

    // Prevent form submission if any validation fails
    if (!isValid) {
        event.preventDefault();
    }
});
