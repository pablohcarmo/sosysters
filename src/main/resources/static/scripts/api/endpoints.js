// API Etnias
const urlEtnias = "/api/etnias";

async function buscarEtnias() {
    const response = await fetch(urlEtnias)
    try {
        if (response.ok){
            console.log("Resposta da API OK");
            const data = await response.json();
            console.log("Dados recebidos: ", data)
            return data;
        } else {
            console.error("Erro na resposta da API:", response.status);
        }
    } catch (error) {
        console.error("Erro ao fazer a requisição:", error);
    }
}

// API Gêneros
const urlGeneros = "/api/generos";

async function buscarGeneros() {
    const response = await fetch(urlGeneros)
    try {
        if (response.ok){
            console.log("Resposta da API OK");
            const data = await response.json();
            console.log("Dados recebidos: ", data)
            return data;
        } else {
            console.error("Erro na resposta da API:", response.status);
        }
    } catch (error) {
        console.error("Erro ao fazer a requisição:", error);
    }
}