import {postRequest, getRequest} from '@/api/axios';

export const userApi = {
    getUser: (userId) => {
        return getRequest(`/user/${userId}`);
    },
    updateUser: (user) => {
        return postRequest(`/user/update`, user);
    },
}

