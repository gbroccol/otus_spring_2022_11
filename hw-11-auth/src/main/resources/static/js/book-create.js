function saveBook() {
    const savedBookContainer = document.getElementById("saved-book");

    const titleInput = document.getElementById("book-title-input").value;
    const authorInput = document.getElementById("book-author-input").value; // Получаем id автора
    const genreInput = document.getElementById("book-genre-input").value; // Получаем id жанра

    // Формируем объект book с вложенными объектами author и genre
    const book = {
        title: titleInput,
        author: {
            id: authorInput // Передаем id автора
        },
        genre: {
            id: genreInput // Передаем id жанра
        }
    };

    fetch("/api/v1/book", {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(book)
    })
    .then(rawResponse => rawResponse.json())
    .then(json => {
        savedBookContainer.innerHTML = JSON.stringify(json, null, 4);
    });
}
