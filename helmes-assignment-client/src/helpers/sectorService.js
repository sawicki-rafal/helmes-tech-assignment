import {httpClient} from './httpClient';

const sectorsPath = 'sectors';

export default {
    getAllSectors() {
        return httpClient.get(sectorsPath);
    }
};
