import React from "react";
import "./style.css";

export const Modal = ({header, children}) => {
    return (
        <div id="myModal" class="modal">
            <div class="modal-content">
                <div class="modal-header">
                    <span class="close">&times;</span>
                    <h2>{header}</h2>
                </div>
                <div class="modal-body">
                    {children}
                </div>

            </div>
        </div>
    );
};
