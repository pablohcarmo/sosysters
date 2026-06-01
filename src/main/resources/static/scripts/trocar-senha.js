document.addEventListener('DOMContentLoaded', function () {
  const submitBtn = document.getElementById('submit-change');
  const formMessage = document.getElementById('form-message');

  function showMessage(text, isError = true) {
    formMessage.textContent = text;
    formMessage.style.color = isError ? '#b00020' : '#00695c';
  }

  submitBtn.addEventListener('click', function (e) {
    e.preventDefault();

    const current = document.getElementById('currentPassword').value.trim();
    const novo = document.getElementById('newPassword').value.trim();
    const conf = document.getElementById('confirmPassword').value.trim();

    // validações front-end
    if (!current || !novo || !conf) {
      showMessage('Preencha todos os campos.');
      return;
    }

    if (novo.length < 8) {
      showMessage('A nova senha deve ter pelo menos 8 caracteres.');
      return;
    }

    if (novo !== conf) {
      showMessage('A confirmação da senha não corresponde.');
      return;
    }

    // Desabilita botão enquanto processa
    submitBtn.classList.add('disabled');
    submitBtn.textContent = 'Enviando...';

    // prepara headers incluindo CSRF se disponível
    const headers = { 'Content-Type': 'application/json' };
    const csrfTokenMeta = document.querySelector('meta[name="_csrf"]');
    const csrfHeaderMeta = document.querySelector('meta[name="_csrf_header"]');
    if (csrfTokenMeta && csrfHeaderMeta) {
      headers[csrfHeaderMeta.getAttribute('content')] = csrfTokenMeta.getAttribute('content');
    }

    // Enviar para o backend (endpoint provisório). O backend deve aceitar JSON.
    fetch('/api/usuario/trocar-senha', {
      method: 'POST',
      headers,
      body: JSON.stringify({ currentPassword: current, newPassword: novo })
    })
      .then(response => {
        if (response.ok) return response.json().catch(() => ({}));
        return response.json().then(err => Promise.reject(err));
      })
      .then(data => {
        showMessage(data.message || 'Senha alterada com sucesso.', false);
        // limpa campos
        document.getElementById('trocar-senha-form').reset();
      })
      .catch(err => {
        const msg = err && err.message ? err.message : 'Erro ao trocar a senha. Verifique sua senha atual.';
        showMessage(msg);
      })
      .finally(() => {
        submitBtn.classList.remove('disabled');
        submitBtn.textContent = 'Trocar senha';
      });
  });
});

