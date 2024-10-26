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
                tdAuthor.innerHTML = bookDto.author.firstName + " " + bookDto.author.lastName;
                tdGenre.innerHTML = bookDto.genre.title;
                tdAction.innerHTML =
                    "<button onclick=deleteBook(" + bookDto.id + ")>delete</button>"
//                    + "<form action=/book/save/" + bookDto.id + "><button type=\"submit\">update</button></form>"
//                    + "<form action=/comment/all/" + bookDto.id + "><button type=\"submit\">comments</button></form>"
                    ;

                tr.append(tdId, tdTitle, tdAuthor, tdGenre, tdAction)
                table.appendChild(tr);
            })
        );
}