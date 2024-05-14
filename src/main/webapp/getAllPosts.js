function getAllPosts() {
    console.log("Fetching all posts");
    fetch("posts")
        .then(data => {
            console.log(data);
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
}
