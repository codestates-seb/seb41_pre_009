import React from "react";

import styles from './RightSidebar.module.css';

const RightSidebar = () => {
    return (
        <ul>
            <li className={styles.member}>
                <a href="https://github.com/YuKyung-Chung" className={styles['member-profile']}>
                    <img
                        src="https://avatars.githubusercontent.com/u/83561356?v=4"
                        alt="정유경"
                    />
                    <div>정유경</div>
                </a>
            </li>
            <li className={styles.member}>
                <a href="https://github.com/kangharyeom" className={styles['member-profile']}>
                    <img
                        src="https://avatars.githubusercontent.com/u/108250233?v=4"
                        alt="강하렴"
                    />
                    <div>강하렴</div>
                </a>
            </li>
            <li className={styles.member}>
                <a href="https://github.com/AAmorning" className={styles['member-profile']}>
                    <img
                        src="https://avatars.githubusercontent.com/u/111403585?v=4"
                        alt="강예은"
                    />
                    <div>강예은</div>
                </a>
            </li>
            <li className={styles.member}>
                <a href="https://github.com/yspark14" className={styles['member-profile']}>
                    <img
                        src="https://avatars.githubusercontent.com/u/111277396?v=4"
                        alt="박영선"
                    />
                    <div>박영선</div>
                </a>
            </li>
            <li className={styles.member}>
                <a href="https://github.com/iltae" className={styles['member-profile']}>
                    <img
                        src="https://avatars.githubusercontent.com/u/106229016?v=4"
                        alt="김일태"
                    />
                    <div>김일태</div>
                </a>
            </li>
        </ul>
    )
}

export default RightSidebar;