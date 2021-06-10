import axios from 'axios';

export default class ClienteService {

    url = "http://localhost:8080/clientes/"

    getAll() {
        return axios.get(this.url + 'all');
    }

    save(cliente) {
        return axios.post(this.url + 'add' , cliente);
    }

    getById(id){
        return axios.get(this.url + id);
    }

    addLinha(dto) {
        return axios.post(this.url + 'addLinha', dto)
    }

    atualizar(id, cliente) {
        return axios.put(this.url + id, cliente)
    }

    delete(id){
        return axios.get(this.url + 'delete/' + id);
    }
}