// API Etnias
const url = "http://localhost:8080/api/etnias";

async function buscarEtnias() {
    const response = await fetch(url)
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