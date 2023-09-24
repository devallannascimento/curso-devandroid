package com.allannascimento.realm.database

import android.content.ClipData
import android.graphics.ColorSpace.Model
import com.allannascimento.realm.model.Usuario
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import org.mongodb.kbson.ObjectId

class DatabaseRealm {

    private val config = RealmConfiguration.create(schema = setOf(Usuario::class))
    private val realm = Realm.open(config)

    fun salvar(usuario: Usuario){
        realm.writeBlocking {
            copyToRealm(usuario)
        }
    }

    fun atualizar(usuario: Usuario){
        realm.writeBlocking {
            val usuarioAtualizar = query<Usuario>("id == $0", usuario.id).find().first()

            usuarioAtualizar.nome = usuario.nome
            usuarioAtualizar.idade = usuario.idade
        }
    }

    fun deletar(id: ObjectId){
        realm.writeBlocking {
            val usuarioDeletar = query<Usuario>("id == $0", id).find().first()

            delete(usuarioDeletar)
        }
    }

    fun listar(): RealmResults<Usuario>{
        return realm.query<Usuario>().find()
    }

}