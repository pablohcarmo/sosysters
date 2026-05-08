const url = "http://localhost:8080/usuarias";

async function APICall() {
    const response = await fetch(url)
    try {
        if (response.ok){
            console.log("Resposta da API OK");
            const data = await response.json();
            console.log("Dados recebidos: ", data)
        } else {
            console.error("Erro na resposta da API:", response.status);
        }
    } catch (error) {
        console.error("Erro ao fazer a requisição:", error);
    }
}

APICall();

// Hamburguer Menu
function toggleMenu() {
    var menu = document.getElementById("menu");
    menu.classList.toggle("active");
    document.body.classList.toggle('menu-open');
}

//JS FEED
let currentIndex1 = 0;
let currentIndex2 = 0;

function showSlide(id, index) {
    const carrossel = document.getElementById(id);
    const totalSlides = carrossel.children.length;
    if (index >= totalSlides) {
        index = 0;
    } else if (index < 0) {
        index = totalSlides - 1;
    }
    carrossel.style.transform = 'translateX(' + (-index * 100) + '%)';
    if (id === 'carrossel1') {
        currentIndex1 = index;
    } else if (id === 'carrossel2') {
        currentIndex2 = index;
    }
}

function nextSlide(id) {
    if (id === 'carrossel1') {
        showSlide(id, currentIndex1 + 1);
    } else if (id === 'carrossel2') {
        showSlide(id, currentIndex2 + 1);
    }
}

function prevSlide(id) {
    if (id === 'carrossel1') {
        showSlide(id, currentIndex1 - 1);
    } else if (id === 'carrossel2') {
        showSlide(id, currentIndex2 - 1);
    }
}