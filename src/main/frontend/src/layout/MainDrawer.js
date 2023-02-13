import {Box, Drawer, List, Toolbar} from "@mui/material";
import * as PropTypes from "prop-types";
import theme from "./mermaid";

theme.layout.drawerWidth = undefined
let InboxIcon;


function Item(props) {
    return null;
}

Item.propTypes = {
    to: PropTypes.string,
    title: PropTypes.string
};
export const MainDrawer = ({drawerOpen, toggleDrawer}) => (
    <Drawer
        open={drawerOpen} onClose={toggleDrawer} variant='permanent'
        sx={{
            width: theme => drawerOpen ? theme.layout.drawerWidth : theme.spacing(7),
            '& .MuiDrawer-paper': theme => ({
                width: theme.layout.drawerWidth,
                ...(!drawerOpen && {
                    width: theme.spacing(7),
                    overflowX: 'hidden'
                })
            })
        }}
    >
        <Toolbar/>
        <Box sx={{overflow: drawerOpen ? 'auto' : 'hidden'}}>
            <List>
                <Item disableTooltip={drawerOpen} Icon={InboxIcon}
                      title='Todo' to='/'/>
            </List>
        </Box>
    </Drawer>
);