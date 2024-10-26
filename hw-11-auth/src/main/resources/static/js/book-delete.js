function deleteBook(id) {

    fetch(`/api/v1/book/` + id, {
        method: 'DELETE'
    })
        .then(response => {
            location.reload()
        })
        .then(response => {
            listBooks();
        });
}
