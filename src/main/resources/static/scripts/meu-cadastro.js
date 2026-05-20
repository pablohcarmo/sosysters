document.addEventListener('DOMContentLoaded', function () {
  const inputCpf = document.getElementById('cpfUsuaria');
  const inputTelefone = document.getElementById('telefoneCompleto');
  const inputCep = document.getElementById('cep');

  function formatCpf(value) {
    const digits = value.replace(/\D/g, '').slice(0, 11);
    if (digits.length <= 3) return digits;
    if (digits.length <= 6) return digits.replace(/(\d{3})(\d+)/, '$1.$2');
    if (digits.length <= 9) return digits.replace(/(\d{3})(\d{3})(\d+)/, '$1.$2.$3');
    return digits.replace(/(\d{3})(\d{3})(\d{3})(\d+)/, '$1.$2.$3-$4');
  }

  if (inputCpf) {
    inputCpf.value = formatCpf(inputCpf.value);
    inputCpf.addEventListener('input', function () {
      this.value = formatCpf(this.value);
    });
  }

  if (inputTelefone) {
    inputTelefone.addEventListener('input', function () {
      const digits = this.value.replace(/\D/g, '').slice(0, 11);
      if (digits.length <= 2) {
        this.value = digits.length ? `(${digits}` : '';
      } else if (digits.length <= 6) {
        this.value = `(${digits.slice(0, 2)}) ${digits.slice(2)}`;
      } else {
        this.value = `(${digits.slice(0, 2)}) ${digits.slice(2, 7)}-${digits.slice(7)}`;
      }
    });
  }

  if (inputCep) {
    inputCep.addEventListener('input', function () {
      this.value = this.value.replace(/\D/g, '').slice(0, 8);
    });
  }
});

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

