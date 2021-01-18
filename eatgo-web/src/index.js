(async () =>{
    const url = "http://localhost:8080/restaurants";
    const response  =  await fetch(url);
    const datas =await response.json();

    const element = document.getElementById("app");
    element.innerHTML = `
        ${datas.map(data => `
            <p>
                ${data.id}
                ${data.name}
                ${data.address}
            </p>
        `).join('-------------------')}
    `;
})()
