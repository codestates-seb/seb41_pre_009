import React from 'react';

import { Public, Stars, Work, Info } from "@mui/icons-material";
import styles from './Sidebar.module.css';

const Sidebar = () => {

    return (
        <div className={styles.sidebar}>
            <div className={styles['sidebar-container']}>
                <div className={styles['sidebar-options']}>
                    <div className={styles['sidebar-option']}>
                        <a>Home</a>
                    </div>
                    <div className={styles['sidebar-option']}>
                        <p>PUBLIC</p>
                        <div className={styles.link}>
                            <div className={styles['link-tag']}>
                                <Public className={styles.public} />
                                <a>Questions</a>
                            </div>
                            <div className={styles.tags}>
                                <p>Tags</p>
                                <p>Users</p>
                                <p>Companies</p>
                            </div>
                        </div>
                    </div>
                    <div className={styles['sidebar-option']}>
                        <p>
                            COLLECTIVES
                            <Info className={styles.info} />
                        </p>
                        <div className={styles.link}>
                            <div className={styles['link-tag']}>
                                <Stars className={styles.stars} />
                                <a>Explore Collectives</a>
                            </div>
                        </div>
                    </div>
                    <div className={styles['sidebar-option']}>
                        <p>
                            TEAMS
                            <Info className={styles.info} />
                        </p>
                        <div className={styles.link}>
                            <div className={styles['link-tag']}>
                                <Work className={styles.work} />
                                <a>Create free Team</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Sidebar;