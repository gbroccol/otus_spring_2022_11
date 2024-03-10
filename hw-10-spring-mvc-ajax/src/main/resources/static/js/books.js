function getAllBooks() {
    const table = document.getElementById('bookTable');

    function createNode(elem) {
        return  document.createElement(elem);
    }

    fetch('/api/v1/book')
        .then((response) => response.json())
        .then((bookDto) => bookDto
            .map(function (bookDto) {
                let tr = createNode('tr')
                let tdId = createNode('td')
                let tdTitle = createNode('td')
                let tdAuthor = createNode('td')
                let tdGenre = createNode('td')
                let tdAction = createNode('td')

                tdId.innerHTML = bookDto.id;
                tdTitle.innerHTML = bookDto.title;
                tdAuthor.innerHTML = bookDto.authorLastName + " " + bookDto.authorFirstName;
                tdGenre.innerHTML = bookDto.genre;
                tdAction.innerHTML =
                    "<form action=/book/save/" + bookDto.id + "><button type=\"submit\">update</button></form>" +
                    "<button onclick=deleteBook(" + bookDto.id + ")>delete</button>" +
                    "<form action=/comment/all/" + bookDto.id + "><button type=\"submit\">comments</button></form>";

                tr.append(tdId, tdTitle, tdAuthor, tdGenre, tdAction)
                table.appendChild(tr);
            })
        );
}

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

function saveBook() {
    const savedBookContainer = document.getElementById("saved-book")

    const titleInput = document.getElementById("book-title-input")

    const book = { title: titleInput.value}

    fetch("/api/v1/book", {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(book)})
    .then(rawResponse => rawResponse.json())
    .then(json => savedBookContainer.innerHTML = JSON.stringify(json, null, 4))
}


// function outputBook(character) {

	// Вывести json
	// const dataContainer = document.getElementById("dataContainer")
	// dataContainer.innerHTML = JSON.stringify(character, undefined, 4)
	// console.log(JSON.stringify(character, undefined, 4));


	// <!--        const characterPhoto = document.getElementById("characterPhoto")-->
	// <!--        characterPhoto.src = character.image;-->
// }

// <!--    function getDataByFetch() {-->
// <!--        fetch('https://rickandmortyapi.com/api/character/4')-->
// <!--            .then(response => response.json())-->
// <!--            .then(json => outputBook(json))-->
// <!--    }-->
