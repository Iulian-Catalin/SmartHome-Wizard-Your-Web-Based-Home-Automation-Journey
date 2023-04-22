function newItemToDo() {
    var name = document.getElementById('name1').value;
    var room = document.getElementById('room1').value;
    var watts = document.getElementById('watts1').value;
    var urlEnc = encodeURI('additem?itemname='+name+'&room='+room+'&watts='+watts)
    console.log(urlEnc);
    $.ajax({url:urlEnc}).done(function (response) {
        location.href = "listMyStuff.jsp";
    });
}

function newRoomToDo() {
    var name = document.getElementById('name2').value;
    var urlEnc = encodeURI('addroom?roomname=' + name)
    $.ajax({
        url: urlEnc
    }).done(function (response) {
        location.href = "listMyStuff.jsp";
    });
}

function loadItemToDo() {
    $.ajax({
        url: 'listitem'
    }).done(function (response) {
        //  printOnDiv(response.listFromBackend);
        display(response.listFromBackend);
        console.log(response.listFromBackend)
    });
}

function loadRoomToDo() {
    $.ajax({
        url: 'listroom'
    }).done(function (response) {
        //  printOnDiv(response.listFromBackend);
        display2(response.listFromBackend);
    });
}

// function deleteAll() {
//     $.ajax({
//         url: 'manageMyToList?action=DELETE'
//     }).done(function (response) {
//         printOnDiv(response.listFromBackend); // ne vom asigura ca din backend ne vine listFromBackend goala
//     });
// }


function display(lista) {
    var randuri = "";
    var toggle = "<label class=\"switch\">\n" +
        "  <input type=\"checkbox\" id=\"toggleOn\" value=\"\" onclick=\"valueToggle()\">\n" +
        "  <span class=\"slider round\"></span>\n" +
        "</label>";
    lista.forEach(function (obiect) {
        randuri += "<tr>" +
            "<td>" + obiect.itemName +obiect.idDB + "</td>" +
            "<td>" + obiect.itemDate + "</td>" +
            "<td>" + obiect.room + "</td>" +
            "<td>" + obiect.watts + "</td>" +
            "<td>" + onToggle(obiect.on) + toggle +"</td>" +
            // "<td> <a href='neverforget?action=delete&id="+obiect.id+"'>x</a></td>" +
            "</tr>";
    });
    $("#obiect1").html(randuri);
}

function display2(lista) {
    var randuri = "";
    lista.forEach(function (obiect) {
        randuri += "<tr>" +
            "<td>" + obiect.roomName + "</td>" +
            // "<td> <a href='neverforget?action=delete&id="+obiect.id+"'>x</a></td>" +
            "</tr>";
    });
    $("#obiect2").html(randuri);
}

function search(myText) {
    $.ajax("listitem", {
        cache: false,
        dataType: "json",
        data: {
            // order: ordinea,
            search: myText
        }
    }).done(function (response) {
        display(response.listFromBackend);
    });
}


function valueToggle() {
    let checkbox = document.getElementById('toggleOn').value;
    var on;
    if (checkbox === 'false') {
        checkbox = 'true';
        document.getElementById('toggleOn').value = checkbox;
        on = checkbox;
    } else { checkbox = 'false';
        document.getElementById('toggleOn').value = checkbox;}
    alert(checkbox)
}

function onToggle(object) {
    document.getElementById('toggleOn').value = object;
    return object;
}