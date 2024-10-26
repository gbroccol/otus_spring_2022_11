function loadAuthors() {
    const authorSelect = document.getElementById("book-author-input");

    fetch('/api/v1/author')
        .then((response) => response.json())
        .then((authors) => {
            authors.forEach((author) => {
                let option = document.createElement("option");
                option.value = author.id;
                option.text = author.firstName + " " + author.lastName;
                authorSelect.appendChild(option);
            });
        });
}
