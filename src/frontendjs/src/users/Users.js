//IMPORT USING THE LATEST ESVERSION6 SYNTAX
import React from 'react';
import {useApi} from '../api';
import {Table, TableBody, TableCell, TableHead, TableRow, IconButton} from '@material-ui/core';
import DeleteIcon from '@material-ui/icons/Delete';

export const AllUsers = () => {

    const api = useApi();

const { data: allUsers } = api.endpoints.getUsers.useQuery(undefined, { pollingInterval: 10000 });
const { data: self } = api.endpoints.getSelf.useQuery();
const [deleteUser] = api.endpoints.deleteUser.useMutation();
{
    allUsers && allUsers.map(user => <TableRow key={user.id}>
        <TableCell>{user.name}</TableCell>
        <TableCell>{new Date(user.created).toLocaleDateString()}</TableCell>
        <TableCell>{user.roles.join(', ')}</TableCell>
        <TableCell align='right'>
            <IconButton
                disabled={user.id === self?.id} onClick={() => deleteUser(user)}
            >
                <DeleteIcon />
            </IconButton>
        </TableCell>
    </TableRow>
    );
}
return (
    <Table>
        <TableHead>
            <TableRow>
                <TableCell>Name</TableCell> 
                <TableCell>Created</TableCell>  
                <TableCell>Roles</TableCell>    
                <TableCell align='right'>Actions</TableCell>    
            </TableRow>
        </TableHead>
        <TableBody>
            {allUsers && allUsers.map(user => <TableRow key={user.id}>
                <TableCell>{user.name}</TableCell>
                <TableCell>{new Date(user.created).toLocaleDateString()}</TableCell>
                <TableCell>{user.roles.join(', ')}</TableCell>
                <TableCell align='right'>
                    <IconButton
                        disabled={user.id === self?.id} onClick={() => deleteUser(user)}
                    >
                        <DeleteIcon />
                    </IconButton>
                </TableCell>
            </TableRow>
            )}
        </TableBody>
    </Table>
);
