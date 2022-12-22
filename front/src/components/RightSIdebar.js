import React from "react";

import styles from './RightSidebar.module.css';

const RightSidebar = () => {
    return (
        <ul>
            <li>
                <a href="https://github.com/YuKyung-Chung" className={styles['member-profile']}>
                    <img
                        src="https://s3.us-west-2.amazonaws.com/secure.notion-static.com/34b2dc58-60b2-4eee-aba0-2f6ce2bf4028/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20221222%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20221222T081112Z&X-Amz-Expires=86400&X-Amz-Signature=54e510c97a0b2f46be36ce097102b05326ea930fb110885a4da50ea0fa9a6b28&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Untitled.png%22&x-id=GetObject"
                        alt="정유경"
                    />
                    <div>정유경</div>
                </a>
            </li>
            <li>
                <a href="https://github.com/kangharyeom" className={styles['member-profile']}>
                    <img
                        src="https://s3.us-west-2.amazonaws.com/secure.notion-static.com/a589c7ca-1d6a-48c2-a0a6-201dd7e46d95/20201209_013058_612.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20221222%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20221222T081128Z&X-Amz-Expires=86400&X-Amz-Signature=d74f0cc027c15de4708637bd9e2fdd4bc9dc0fc760a3ea8d575fc24650aa9e8c&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%2220201209_013058_612.jpg%22&x-id=GetObject"
                        alt="강하렴"
                    />
                    <div>강하렴</div>
                </a>
            </li>
            <li>
                <a href="https://github.com/AAmorning" className={styles['member-profile']}>
                    <img
                        src="https://s3.us-west-2.amazonaws.com/secure.notion-static.com/ef952113-1280-4cfe-8902-d74ec46ffec7/IMG_5883.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20221222%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20221222T081155Z&X-Amz-Expires=86400&X-Amz-Signature=353fc90912d88fe144ecf6ff7b50f74ae0da1866fec3e53509d18e31c85e983b&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22IMG_5883.jpg%22&x-id=GetObject"
                        alt="강예은"
                    />
                    <div>강예은</div>
                </a>
            </li>
            <li>
                <a href="https://github.com/yspark14" className={styles['member-profile']}>
                    <img
                        src="https://s3.us-west-2.amazonaws.com/secure.notion-static.com/4a1ab412-0d63-4873-8706-c33842b69d14/IMG_9661.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20221222%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20221222T081228Z&X-Amz-Expires=86400&X-Amz-Signature=b381a0dc2b365bef1fe539925d2ab2b7cafb20d2ddd68f85f6990e77cd591c31&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22IMG_9661.JPG.jpg%22&x-id=GetObject"
                        alt="박영선"
                    />
                    <div>박영선</div>
                </a>
            </li>
            <li>
                <a href="https://github.com/iltae" className={styles['member-profile']}>
                    <img
                        src="https://s3.us-west-2.amazonaws.com/secure.notion-static.com/4d9de4dc-6b78-4377-8593-89bc9055ee4f/FEADCFC5-7329-40EB-AED2-17CB56F06195.jpeg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20221222%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20221222T080946Z&X-Amz-Expires=86400&X-Amz-Signature=4d994b87aee8cc3ccc9248e9a4177713efa0b3c938252c868743d0cd0f377048&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22FEADCFC5-7329-40EB-AED2-17CB56F06195.jpeg%22&x-id=GetObject"
                        alt="김일태"
                    />
                    <div>김일태</div>
                </a>
            </li>
        </ul>
    )
}

export default RightSidebar;