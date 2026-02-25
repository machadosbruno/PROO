using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace exercicio
{
    internal class Aluno
    {
        private string prontuario {  get; set; }
        private string nome { get; set; }
        private string dataNascimento { get; set; }

        public string DataNascimento
        {
            get {  return dataNascimento; }
            set
            {
                if (value != null)
                {
                    String[] partes = value.Split('/');
                    if (int.Parse(partes[2]) > 1930)
                    {
                        dataNascimento = value;
                    }
                }
            }
        }

        public Aluno(string _pronturio)
        {
            prontuario = _pronturio;
        }

        public Aluno(string _prontuario, string _nome, string _dataNascimento)
        {
            prontuario = _prontuario;
            nome = _nome;
            dataNascimento = _dataNascimento;
        }
    }
}
