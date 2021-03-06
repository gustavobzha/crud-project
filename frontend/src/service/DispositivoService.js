import axios from 'axios';

export default class DispositivosService {

    url = "http://localhost:8080/dispositivos/"

    getAll() {
        return axios.get(this.url + 'all');
    }

    getLivres() {
        return axios.get(this.url + 'livres');
    }

    save(estrutura) {
        return axios.post(this.url + 'add' , estrutura);
    }

    delete(id){
        return axios.get(this.url + 'delete/' + id);
    }
}