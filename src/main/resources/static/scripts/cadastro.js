// Função que valida a lógica matemática do CPF
function validarCPF(cpf) {
    cpf = cpf.replace(/[^\d]+/g, '');
    if (cpf.length !== 11 || !!cpf.match(/(\d)\1{10}/)) return false;

    let soma = 0, resto;
    for (let i = 1; i <= 9; i++) soma += parseInt(cpf.substring(i-1, i)) * (11 - i);
    resto = (soma * 10) % 11;
    if ((resto === 10) || (resto === 11)) resto = 0;
    if (resto !== parseInt(cpf.substring(9, 10))) return false;

    soma = 0;
    for (let i = 1; i <= 10; i++) soma += parseInt(cpf.substring(i-1, i)) * (12 - i);
    resto = (soma * 10) % 11;
    if ((resto === 10) || (resto === 11)) resto = 0;
    if (resto !== parseInt(cpf.substring(10, 11))) return false;

    return true;
}

// Função que aplica a máscara visual
function aplicarMascaraCPF(input) {
    let valor = input.value.replace(/\D/g, "");
    if (valor.length > 3 && valor.length <= 6) {
        valor = valor.replace(/(\d{3})(\d+)/, "$1.$2");
    } else if (valor.length > 6 && valor.length <= 9) {
        valor = valor.replace(/(\d{3})(\d{3})(\d+)/, "$1.$2.$3");
    } else if (valor.length > 9) {
        valor = valor.replace(/(\d{3})(\d{3})(\d{3})(\d+)/, "$1.$2.$3-$4");
    }
    input.value = valor;
}

// Coletar dados do formulário HTML
function coletarDadosFormulario() {
    return {
        nomeUsuaria: document.getElementById("nome").value,
        sobrenomeUsuaria: document.getElementById("sobrenome").value,
        nomeSocialUsuaria: document.getElementById("nomeSocial").value || null,
        dtNascimentoUsuaria: document.getElementById("dataNascimento").value,
        rgUsuaria: document.getElementById("rg").value,
        cpfUsuaria: document.getElementById("cpf").value.replace(/\D/g, ""), 
        emailUsuaria: document.getElementById("email").value,
        senhaUsuaria: document.getElementById("senha").value,
        etniaUsuaria: parseInt(document.getElementById("etnia").value) || null,
        generoUsuaria: parseInt(document.getElementById("genero").value) || null,

        // telefone (campo único: DDD + número). Backend espera dddCelular (2 digits) and celular (number without DDD)
        dddCelular: (function() {
            const el = document.getElementById("celular");
            if (!el) return null;
            const digits = el.value.replace(/\D/g, '').slice(0,11);
            return digits.length >= 2 ? digits.slice(0,2) : null;
        })(),
        celular: (function() {
            const el = document.getElementById("celular");
            if (!el) return null;
            const digits = el.value.replace(/\D/g, '').slice(0,11);
            return digits.length > 2 ? digits.slice(2) : null;
        })(),

        // endereco
        cep: document.getElementById("cep") ? document.getElementById("cep").value.replace(/\D/g, "") : null,
        logradouro: document.getElementById("logradouro") ? document.getElementById("logradouro").value : null,
        numeroResidencia: document.getElementById("numero") ? document.getElementById("numero").value : null,
        complemento: document.getElementById("complemento") ? document.getElementById("complemento").value : null,
        bairro: document.getElementById("bairro") ? document.getElementById("bairro").value : null,
        cidade: document.getElementById("cidade") ? document.getElementById("cidade").value : null,
        estado: document.getElementById("estado") ? document.getElementById("estado").value : null
    };
}

// Função para receber os dados
async function cadastrarUsuario(dadosUsuario) {
    try {
        const response = await fetch('/usuarias', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
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
    }
}

// Configuração quando a página carrega
document.addEventListener('DOMContentLoaded', function() {
    carregarEtnia();
    carregarGenero();

    // CPF: Máscara e Validação Visual em Tempo Real
    const campoCpf = document.getElementById("cpf");
    if (campoCpf) {
        campoCpf.addEventListener("input", function() {
            aplicarMascaraCPF(this);
            
            // Feedback visual de borda
            if (this.value.length === 14) {
                if (validarCPF(this.value)) {
                    this.style.borderColor = "#28a745"; // Verde se válido
                } else {
                    this.style.borderColor = "#dc3545"; // Vermelho se inválido
                }
            } else {
                this.style.borderColor = ""; // Padrão enquanto digita
            }
        });
    }

    // Formatação do telefone (DDD + número) em um único campo: (DD) NNNNN-NNNN
    const inputTel = document.getElementById("celular");
    if (inputTel) {
        inputTel.addEventListener("input", function() {
            let digits = this.value.replace(/\D/g, '').slice(0,11); // max 11 digits (2 DDD + 9 number)
            if (digits.length <= 2) {
                this.value = digits.length ? `(${digits}` : ""; // partial DDD like (1 or (11
            } else if (digits.length <= 6) {
                // DDD + up to 4 digits of number
                this.value = `(${digits.slice(0,2)}) ${digits.slice(2)}`;
            } else {
                // full formatting: (DD) 9XXXX-XXXX
                this.value = `(${digits.slice(0,2)}) ${digits.slice(2,7)}-${digits.slice(7)}`;
            }
        });
        inputTel.addEventListener("paste", function(e) {
            e.preventDefault();
            const pasted = (e.clipboardData || window.clipboardData).getData('text');
            const digits = pasted.replace(/\D/g, '').slice(0,11);
            if (digits.length <= 2) this.value = digits.length ? `(${digits}` : "";
            else if (digits.length <= 6) this.value = `(${digits.slice(0,2)}) ${digits.slice(2)}`;
            else this.value = `(${digits.slice(0,2)}) ${digits.slice(2,7)}-${digits.slice(7)}`;
        });
    }

    // VIA CEP
    document.getElementById("cep").addEventListener("blur", async function () {
        const cep = this.value.replace(/\D/g, "");
        if (cep.length !== 8) return;

        try {
            const response = await fetch(`https://viacep.com.br/ws/${cep}/json/`);
            const data = await response.json();
            if (data.erro) {
                alert("CEP não encontrado");
                return;
            }
            document.getElementById("logradouro").value = data.logradouro;
            document.getElementById("bairro").value = data.bairro;
            document.getElementById("cidade").value = data.localidade;
            document.getElementById("estado").value = data.uf;
        } catch (error) {
            console.error("Erro ao buscar CEP:", error);
        }
    });

    // VALIDAÇÕES
    const formulario = document.getElementById('meuFormulario');
    formulario.addEventListener('submit', async function(event) {
        event.preventDefault();

        // 1. Validação do CPF
        const cpfRaw = document.getElementById("cpf").value;
        if (!validarCPF(cpfRaw)) {
            alert('Por favor, insira um CPF válido.');
            document.getElementById("cpf").focus();
            return;
        }

        // 2. Validação da Etnia
        const etniaValue = document.getElementById("etnia").value;
        if (!etniaValue || etniaValue === "") {
            alert('Por favor, selecione uma etnia.');
            document.getElementById("etnia").focus();
            return;
        }

        // 3. Validação do Gênero
        const generoValue = document.getElementById("genero").value;
        if (!generoValue || generoValue === "") {
            alert('Por favor, selecione um gênero.');
            document.getElementById("genero").focus();
            return;
        }

        // Se passou por todas as validações, envia os dados
        try {
            const dados = coletarDadosFormulario();
            await cadastrarUsuario(dados);
            // Mostrar diálogo informando que o link de confirmação foi enviado
            if (confirm('Um link de confirmação foi enviado para o seu e-mail. AO clicar em OK, você será redirecionado para o login.')) {
                window.location.href = '/login';
            } else {
                formulario.reset();
                document.getElementById("cpf").style.borderColor = "";
            }
        } catch (error) {
            alert('Erro no cadastro: ' + error.message);
        }
    });
});