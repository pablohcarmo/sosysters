// Coletar dados do formulário HTML
function coletarDadosFormulario() {
    return {
        nomeUsuaria: document.getElementById("nome").value,
        sobrenomeUsuaria: document.getElementById("sobrenome").value,
        nomeSocialUsuaria: document.getElementById("nomeSocial").value || null,
        dtNascimentoUsuaria: document.getElementById("dataNascimento").value,
        rgUsuaria: document.getElementById("rg").value,
        cpfUsuaria: document.getElementById("cpf").value,
        emailUsuaria: document.getElementById("email").value,
        senhaUsuaria: document.getElementById("senha").value,
        etniaUsuaria: parseInt(document.getElementById("etnia").value) || null,
        generoUsuaria: parseInt(document.getElementById("genero").value) || null
    };
}

// Função para receber os dados
async function cadastrarUsuario(dadosUsuario) {
    try {
        const response = await fetch('http://localhost:8080/usuarias', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dadosUsuario)
        });
        
        if (!response.ok) {
            throw new Error(`Erro: ${response.status}`);
        }

        return await response.text();
    } catch (error) {
        console.error('Erro ao enviar dados:', error);
        throw error;
    }
}

// Popula o select de etnias do back-end
async function carregarEtnia() {
    try {
        const etnias = await buscarEtnias();
        const selectEtnia = document.getElementById('etnia');

        selectEtnia.innerHTML = '<option value="">Selecione uma etnia</option>';
        etnias.forEach(etnia => {
            const option = document.createElement('option');
            option.value = etnia.idEtnia;
            option.textContent = etnia.etnia;
            selectEtnia.appendChild(option);
        });
    } catch (error) {
        console.error('Erro ao carregar etnias:', error);
        alert('Erro ao carregar etnias');
    }
}

// Popula o select de gêneros do back-end
async function carregarGenero() {
    try {
        const generos = await buscarGeneros();
        const selectGeneros = document.getElementById('genero');

        selectGeneros.innerHTML = '<option value="">Selecione</option>';
        generos.forEach(genero => {
            const option = document.createElement('option');
            option.value = genero.idGenero;
            option.textContent = genero.genero;
            selectGeneros.appendChild(option);
        });
    } catch (error) {
        console.error('Erro ao carregar generos:', error);
        alert('Erro ao carregar generos');
    }
}

// Configuração quando a página carrega
document.addEventListener('DOMContentLoaded', function() {
    carregarEtnia();
    carregarGenero();

    const formulario = document.getElementById('meuFormulario');
    
    formulario.addEventListener('submit', async function(event) {
        event.preventDefault();

        const dadosUsuario = coletarDadosFormulario();

        // Validação básica
        if (!dadosUsuario.etniaUsuaria) {
            alert('Por favor, selecione uma etnia.');
            return;
        }
        
        if (!dadosUsuario.generoUsuaria) {
            alert('Por favor, selecione um gênero.');
            return;
        }

        try {
            await cadastrarUsuario(dadosUsuario);
            alert('Usuária cadastrada com sucesso!');
            formulario.reset(); // Limpar formulário após sucesso
        } catch (error) {
            alert('Erro no cadastro: ' + error.message);
        }
    });
});