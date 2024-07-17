
document.addEventListener('DOMContentLoaded', async () => {
    const options = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    };
    const response = await fetch('http://localhost:8080/app/peliculas', options);
    const data = await response.json();
    console.log(data);
    const movies = data;
    
    const tbody = document.getElementById('bodyTablePeliculas');
    movies.forEach(movie => {
        const tr = document.createElement('tr');
        const tdTitle = document.createElement('td');
        tdTitle.textContent = movie.titulo;
        const tdDuration = document.createElement('td');
        tdDuration.textContent = movie.duracion;
        const tdGenres = document.createElement('td');
        tdGenres.textContent = movie.genero;
        const tdImage = document.createElement('td');
        const img = document.createElement('img');
        img.src = "../assets/img/" + movie.imagen;
        img.width = '150';
        img.alt = movie.titulo;
        tdImage.appendChild(img);
        img.classList.add('img-fluid');
        img.classList.add('img-thumbnail');
        tr.appendChild(tdTitle);
        tr.appendChild(tdDuration);
        tr.appendChild(tdGenres);
        tr.appendChild(tdImage);
        tbody.appendChild(tr);

    });
});