using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace exercicio
{
    internal class Livro
    {
        private int ISBN { get; set; }
        private string titulo {  get; set; }
        private int anoPublicacao { get; set; }
        private bool locado { get; set; }

        public Livro (int _ISBN)
        {
            ISBN = _ISBN;
        }
        public Livro (int _ISBN, string _titulo, int _anoPublicacao, bool _locado)
        {
            ISBN = _ISBN;
            titulo = _titulo;
            if (_ISBN <= 2026)
            {
                anoPublicacao = _anoPublicacao;
            }
            else
            {
                anoPublicacao = 0;
            }
            locado = _locado;
        }
    }
}
