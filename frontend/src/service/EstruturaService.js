import axios from 'axios';

export default class EstruturaService {

    url = "http://localhost:8080/estruturas/"

    getAll() {
        return axios.get(this.url + 'all');
    }

    getEstruturasLinha(id){
        return axios.get(this.url + 'linha/' + id);
    }

    addDispositivos(dto) {
        return axios.post(this.url + 'addDispositivos', dto)
    }

    save(estrutura) {
        return axios.post(this.url + 'add' , estrutura);
    }

    delete(id){
        return axios.get(this.url + 'delete/' + id);
    }
}