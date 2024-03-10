// // Находим элементы на странице
// const form = document.querySelector('#form');
// const taskInput = document.querySelector('#taskInput');
// const tasksList = document.querySelector('#tasksList');
// const emptyList = document.querySelector('#emptyList');

// let tasks = [];

// if (localStorage.getItem('tasks')) {
// 	tasks = JSON.parse(localStorage.getItem('tasks'));
// 	tasks.forEach((task) => renderTask(task));
// }

// checkEmptyList();

// form.addEventListener('submit', addTask);
// tasksList.addEventListener('click', deleteTask);
// tasksList.addEventListener('click', doneTask);

// // Функции
// function addTask(event) {
// 	// Отменяем отправку формы
// 	event.preventDefault();

// 	// Достаем текст задачи из поля ввода
// 	const taskText = taskInput.value;

// 	// Описываем задачу в виде объекта
// 	const newTask = {
// 		id: Date.now(),
// 		text: taskText,
// 		done: false,
// 	};

// 	// Добавляем задачу в массив с задачами
// 	tasks.push(newTask);

// 	// Сохраняем список задач в хранилище браузера localStorage
// 	saveToLocalStorage();

// 	// Рендерим задачу на странице
// 	renderTask(newTask);

// 	// Очищаем поле ввода и возвращаем на него фокус
// 	taskInput.value = '';
// 	taskInput.focus();

// 	checkEmptyList();
// }

// function deleteTask(event) {
// 	// Проверяем если клик был НЕ по кнопке "удалить задачу"
// 	if (event.target.dataset.action !== 'delete') return;

// 	const parenNode = event.target.closest('.list-group-item');

// 	// Определяем ID задачи
// 	const id = Number(parenNode.id);

// 	// Удаляем задча через фильтрацию массива
// 	tasks = tasks.filter((task) => task.id !== id);

// 	// Сохраняем список задач в хранилище браузера localStorage
// 	saveToLocalStorage();

// 	// Удаляем задачу из разметки
// 	parenNode.remove();

// 	checkEmptyList();
// }

// function doneTask(event) {
// 	// Проверяем что клик был НЕ по кнопке "задача выполнена"
// 	if (event.target.dataset.action !== 'done') return;

// 	const parentNode = event.target.closest('.list-group-item');

// 	// Определяем ID задачи
// 	const id = Number(parentNode.id);
// 	const task = tasks.find((task) => task.id === id);
// 	task.done = !task.done;

// 	// Сохраняем список задач в хранилище браузера localStorage
// 	saveToLocalStorage();

// 	const taskTitle = parentNode.querySelector('.task-title');
// 	taskTitle.classList.toggle('task-title--done');
// }

// function checkEmptyList() {
// 	if (tasks.length === 0) {
// 		const emptyListHTML = `<li id="emptyList" class="list-group-item empty-list">
// 					<img src="./img/leaf.svg" alt="Empty" width="48" class="mt-3">
// 					<div class="empty-list__title">Список дел пуст</div>
// 				</li>`;
// 		tasksList.insertAdjacentHTML('afterbegin', emptyListHTML);
// 	}

// 	if (tasks.length > 0) {
// 		const emptyListEl = document.querySelector('#emptyList');
// 		emptyListEl ? emptyListEl.remove() : null;
// 	}
// }

// function saveToLocalStorage() {
// 	localStorage.setItem('tasks', JSON.stringify(tasks))
// }

// function renderTask(task) {
// 	// Формируем CSS класс
// 	const cssClass = task.done ? 'task-title task-title--done' : 'task-title';

// 	// Формируем разметку для новой задачи
// 	const taskHTML = `
//                 <li id="${task.id}" class="list-group-item d-flex justify-content-between task-item">
// 					<span class="${cssClass}">${task.text}</span>
// 					<div class="task-item__buttons">
// 						<button type="button" data-action="done" class="btn-action">
// 							<img src="./img/tick.svg" alt="Done" width="18" height="18">
// 						</button>
// 						<button type="button" data-action="delete" class="btn-action">
// 							<img src="./img/cross.svg" alt="Done" width="18" height="18">
// 						</button>
// 					</div>
// 				</li>`;

// 	// Добавляем задачу на страницу
// 	tasksList.insertAdjacentHTML('beforeend', taskHTML);
// }


console.log('hello world 2');



function outputBook(character) {

	// Вывести json
	// const dataContainer = document.getElementById("dataContainer")
	// dataContainer.innerHTML = JSON.stringify(character, undefined, 4)
	console.log(JSON.stringify(character, undefined, 4));


	// <!--        const characterPhoto = document.getElementById("characterPhoto")-->
	// <!--        characterPhoto.src = character.image;-->
}

// <!--    function getDataByFetch() {-->
// <!--        fetch('https://rickandmortyapi.com/api/character/4')-->
// <!--            .then(response => response.json())-->
// <!--            .then(json => outputBook(json))-->
// <!--    }-->



// рабочий код ниже

function listBooks() {
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

                tdId.innerHTML = bookDto.bookId;
                tdTitle.innerHTML = bookDto.title;
                tdAuthor.innerHTML = bookDto.authorLastName + " " + bookDto.authorFirstName;
                tdGenre.innerHTML = bookDto.genre;
                tdAction.innerHTML =
                    "<ul> <form action=/book/save/" + bookDto.bookId + "><button type=\"submit\">update</button></form>" +
                    "<ul> <button onclick=deleteBook(" + bookDto.bookId + ")>delete</button></ul>" +
                    "<ul> <form action=/comment/all/" + bookDto.bookId + "><button type=\"submit\">comments</button></form>";

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
