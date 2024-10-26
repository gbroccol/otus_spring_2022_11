function loadGenres() {
    const genreSelect = document.getElementById("book-genre-input");

    fetch('/api/v1/genre')
        .then((response) => response.json())
        .then((genres) => {
            genres.forEach((genre) => {
                let option = document.createElement("option");
                option.value = genre.id;
                option.text = genre.title;
                genreSelect.appendChild(option);
            });
        });
}
