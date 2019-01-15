package Beans

class Usuario {

    var nome : String = ""
    var email : String = ""
    var senha : String = ""

    constructor(nome: String, email: String, senha: String) {
        this.nome = nome
        this.email = email
        this.senha = senha
    }

    constructor(email: String, senha: String) {
        this.email = email
        this.senha = senha
    }

    constructor(){}

    fun EncodeString(){
        this.email = this.email.replace(".", ",")
    }

    fun DecodeString(){
        this.email = this.email.replace(",", ".")
    }
}

