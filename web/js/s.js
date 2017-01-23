var d = document;

var id;
var name;
var login;
var role;

function addRow() {
// Считываем значения с формы
    id = d.getElementById('id').value;
    name = d.getElementById('name').value;
    login = d.getElementById('login').value;
    role = d.getElementById('role').value;

// Находим нужную таблицу
    var tbody = d.getElementById('tab1').getElementsByTagName('TBODY')[0];

// Создаем строку таблицы и добавляем ее
    var row = d.createElement("TR");
    tbody.appendChild(row);

// Создаем ячейки в вышесозданной строке
// и добавляем тх
    var td1 = d.createElement("TD");
    var td2 = d.createElement("TD");
    var td3 = d.createElement("TD");
    var td4 = d.createElement("TD");
    var td5 = d.createElement("TD");


    row.appendChild(td1);
    row.appendChild(td2);
    row.appendChild(td3);
    row.appendChild(td4);
    row.appendChild(td5);

    td5.setAttribute('onmousedown', 'this.parentNode.parentNode.removeChild (this.parentNode);');

// Наполняем ячейки
    td1.innerHTML = id;
    td2.innerHTML = name;
    td3.innerHTML = login;
    td4.innerHTML = role
    td5.innerHTML = 'Удалить';
}