import axios from 'axios';

export default class ClienteService {

    url = "http://localhost:8080/clientes/"

    getAll() {
        return axios.get(this.url + 'all');
    }

    save(cliente) {
        return axios.post(this.url + 'add' , cliente);
    }

    delete(id){
        return axios.get(this.url + 'delete/' + id);
    }
}