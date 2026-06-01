document.addEventListener("DOMContentLoaded", function() {
    // Smooth scroll para links de navegação
    const navLinks = document.querySelectorAll('a[href^="#"]');

    navLinks.forEach(link => {
        link.addEventListener('click', function(e) {
            const href = this.getAttribute('href');

            // Pula o evento apenas se for um link de verdade com hash
            if (href !== '#' && !href.includes('http')) {
                e.preventDefault();
                const target = document.querySelector(href);

                if (target) {
                    const offsetTop = target.offsetTop - 80; // 80px de offset para o header fixo
                    window.scrollTo({
                        top: offsetTop,
                        behavior: 'smooth'
                    });
                }
            }
        });
    });

    // Animação de fade-in ao scroll
    const observerOptions = {
        threshold: 0.1,
        rootMargin: '0px 0px -50px 0px'
    };

    const observer = new IntersectionObserver(function(entries) {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add('fade-in');
                observer.unobserve(entry.target);
            }
        });
    }, observerOptions);

    // Observar elementos que devem ter fade-in
    const fadeElements = document.querySelectorAll(
        '.sobre-item, .feature-card, .section-title'
    );

    fadeElements.forEach(el => {
        el.style.opacity = '0';
        el.style.animation = 'fadeInUp 0.6s ease-out forwards';
        observer.observe(el);
    });

    // Destacar link ativo no menu ao scroll
    const sections = document.querySelectorAll('section[id]');

    window.addEventListener('scroll', function() {
        let current = '';

        sections.forEach(section => {
            const sectionTop = section.offsetTop;
            const sectionHeight = section.clientHeight;

            if (pageYOffset >= sectionTop - 200) {
                current = section.getAttribute('id');
            }
        });

        navLinks.forEach(link => {
            link.classList.remove('active');
            if (link.getAttribute('href') === '#' + current) {
                link.classList.add('active');
            }
        });
    });

    // Efeito hover nos buttons
    const buttons = document.querySelectorAll('.btn');

    buttons.forEach(btn => {
        btn.addEventListener('mouseenter', function() {
            this.style.transform = 'translateY(-3px)';
        });

        btn.addEventListener('mouseleave', function() {
            this.style.transform = 'translateY(0)';
        });
    });

    // Contador simples de cliques em CTAs para analytics (opcional)
    const ctaButtons = document.querySelectorAll('[th\\:href*="cadastro"], [th\\:href*="auth"]');

    ctaButtons.forEach(btn => {
        btn.addEventListener('click', function() {
            console.log('CTA clicado:', this.textContent);
            // Aqui você pode adicionar tracking/analytics se desejar
        });
    });

    // Preloader fade-out
    window.addEventListener('load', function() {
        document.body.style.opacity = '1';
    });
});

// Adicionar estilos de animação dinamicamente
const style = document.createElement('style');
style.textContent = `
    @keyframes fadeInUp {
        from {
            opacity: 0;
            transform: translateY(20px);
        }
        to {
            opacity: 1;
            transform: translateY(0);
        }
    }

    .fade-in {
        animation: fadeInUp 0.6s ease-out forwards !important;
    }

    .nav-link.active {
        color: var(--cor-botao);
        border-bottom: 2px solid var(--cor-botao);
        padding-bottom: 0.5rem;
    }

    body {
        opacity: 0.95;
    }

    body.loaded {
        opacity: 1;
    }
`;
document.head.appendChild(style);