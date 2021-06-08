import axios from 'axios';

export default class EstruturaService {

    url = "http://localhost:8080/estruturas/"

    getAll() {
        return axios.get(this.url + 'all');
    }

    save(estrutura) {
        return axios.post(this.url + 'add' , estrutura);
    }

    delete(id){
        return axios.get(this.url + 'delete/' + id);
    }
}