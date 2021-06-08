import axios from 'axios';

export default class LinhaService {

    url = "http://localhost:8080/linhas/"

    getAll() {
        return axios.get(this.url + 'all');
    }

    save(linha) {
        return axios.post(this.url + 'add' , linha);
    }

    delete(id){
        return axios.get(this.url + 'delete/' + id);
    }
}