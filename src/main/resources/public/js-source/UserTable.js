// api url
const api_url =
    "http://localhost:8080/api/v1/appUsers/";

// Defining async function
async function getapi(url) {

    // Storing response
    const response = await fetch(url);

    // Storing data in form of JSON
    var data = await response.json();
    console.log(data);
    if (response) {
        hideloader();
    }
    show(data);
}
// Calling that async function
getapi(api_url);

// Function to hide the loader
function hideloader() {
    document.getElementById('loading').style.display = 'none';
}

// Function to define innerHTML for HTML table
function show(data) {
    let tab =
        `<tr>
          <th>userId</th>
          <th>userName</th>
          <th>encrytedPassword</th>
          <th>enabled</th>
         </tr>`;

    // Loop to access all rows
    for (let r of data) {
        tab += `<tr>
    <td>${r.userId} </td>
    <td>${r.userName}</td>
    <td>${r.encrytedPassword}</td>
    <td>${r.enabled}</td>
</tr>`;
    }

    // Setting innerHTML as tab variable
    document.getElementById("appUsers").innerHTML = tab;

}