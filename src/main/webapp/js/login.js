/**
 * view-controller for login.html
 * @author Albin Smrqaku
 */
document.addEventListener("DOMContentLoaded", () => {
    readPublishers();
    readBook();
//todo
    document.getElementById("bookeditForm").addEventListener("submit", saveBook);
    document.getElementById("cancel").addEventListener("click", cancelEdit);
});

/**
 * logs the user in
 */
function saveBook(event) {
    event.preventDefault();
//todo
    const bookForm = document.getElementById("bookeditForm");
    const formData = new FormData(bookForm);
    const data = new URLSearchParams(formData);

    let method;
    let url = "./resource/book/";
    const bookUUID = getQueryParam("uuid");
    if (bookUUID == null) {
        method = "POST";
        url += "create";
    } else {
        method = "PUT";
        url += "update";
    }

    fetch(url,
        {
            method: method,
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: data
        })
        .then(function (response) {
            if (!response.ok) {
                console.log(response);
            } else return response;
        })
        .then()
        .catch(function (error) {
            console.log(error);
        });
}
