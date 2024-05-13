// Get the button and form elements
var showFormButton = document.getElementById('showFormButton');
var postForm = document.getElementById('postForm');

// Add click event listener to the button
showFormButton.addEventListener('click', function() {
    // Toggle the visibility of the form
    if (postForm.style.display === 'none') {
        postForm.style.display = 'block';
    } else {
        postForm.style.display = 'none';
    }
});