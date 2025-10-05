// Coletar dados do formulário HTML
function coletarDadosFormulario() {
    return {
        nome: document.getElementById("nome").value,
        sobrenome: document.getElementById("sobrenome").value,
        nomeSocial: document.getElementById("nomeSocial").value,
        dataNascimento: document.getElementById("dataNascimento").value,
        rg: document.getElementById("rg").value,
        cpf: document.getElementById("cpf").value,
        email: document.getElementById("email").value,
        senha: document.getElementById("senha").value,
        etnia: document.getElementById("etnia").value,
        genero: document.getElementById("genero").value
    };
}

// Função para receber os dados
async function cadastrarUsuario(dadosUsuario) {
    try {
        const response = await fetch('/api/usuarios', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dadosUsuario)
        });
        
        if (!response.ok) {
            throw new Error(`Erro: ${response.status}`);
        }

        return await response.json();
    } catch (error) {
        console.error('Erro ao enviar dados:', error);
        throw error;
    }
}

// Popula o select de etnias
async function carregarEtnia() {
    try {
        const etnias = await buscarEtnias();
        const selectEtnia = document.getElementById('etnia');

        selectEtnia.innerHTML = '<option value="">Selecione uma etnia</option>';
        etnias.forEach(etnia => {
            const option = document.createElement('option');
            option.value = etnia.id;
            option.textContent = etnia.etnia;
            selectEtnia.appendChild(option);
        });
    } catch (error) {
        console.error('Erro ao carregar etnias:', error);
        alert('Erro ao carregar etnias');
    }
}

// Configuração quando a página carrega
document.addEventListener('DOMContentLoaded', function() {
    // Carrega as etnias primeiro
    carregarEtnia();

    // Configura o envio do formulário
    const formulario = document.getElementById('meuFormulario');
    
    formulario.addEventListener('submit', async function(event) {
        event.preventDefault();

        // Coleta dos dados do formulário
        const dadosUsuario = coletarDadosFormulario();

        // Envio dos dados para o back-end
        try {
            await cadastrarUsuario(dadosUsuario);
            alert('Usuário cadastrado com sucesso!');
        } catch (error) {
            alert('Erro no cadastro: ' + error.message);
        }
    });
});