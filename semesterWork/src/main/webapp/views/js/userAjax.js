function renderTable(users, table) {
    $('#table_caption').text("Users List")

    let innerHtml = '<thead>' +
        '               <tr>\n' +
        '                   <th>ID</th>' +
        '                   <th>FirstName</th>' +
        '                   <th>LastName</th>' +
        '                   <th>Email</th>' +
        '               </tr>' +
        '           </thead>';

    for (let i = 0; i < users.length; i++) {
        innerHtml += '<tr>';
        innerHtml += '  <td>' + users[i]['id'] + '</td>';
        innerHtml += '  <td>' + users[i]['firstName'] + '</td>';
        innerHtml += '  <td>' + users[i]['lastName'] + '</td>';
        innerHtml += '  <td>' + users[i]['email'] + '</td>';
        innerHtml += '</tr>';
    }

    table.html(innerHtml);
}

function sendUser(firstName, lastName, email) {

    let errorBlock = $('#error_block')
    errorBlock.hide()
    if (firstName === "" || lastName === "" || email === "") {
        errorBlock.show()
        $('#error').text("Some field is empty")
        return
    }

    let data = {
        "firstName": firstName,
        "lastName": lastName,
        "email": email
    };

    $.ajax({
        type: "POST",
        url: "/users",
        data: JSON.stringify(data),
        success: function (response) {
            renderTable(response, $('#table'))
        },
        dataType: "json",
        contentType: "application/json"
    });
}
