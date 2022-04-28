import {httpClient} from './httpClient';

const sectorEntriesPath = 'sector-entries';

export default {
    getAllSectorEntries() {
        return httpClient.get(sectorEntriesPath);
    },
    saveSectorEntry(sectorEntry) {
        return httpClient.post(sectorEntriesPath, sectorEntry);
    },
    updateSectorEntry(sectorEntry) {
        return httpClient.put(sectorEntriesPath, sectorEntry);
    }
};
