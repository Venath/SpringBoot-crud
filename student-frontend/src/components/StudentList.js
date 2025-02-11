import React, { useEffect, useState } from 'react';
import { getStudents, deleteStudent } from '../services/studentService';

const StudentList = () => {
    const [students, setStudents] = useState([]);

    useEffect(() => {
        fetchStudents();
    }, []);

    const fetchStudents = async () => {
        const response = await getStudents();
        console.log(response.data);
        setStudents(response.data);
    };

    const handleDelete = async (id) => {
        await deleteStudent(id);
        fetchStudents();
    };

    return (
        <div>
            <h2>Student List</h2>
            <ul>
                {students.map((student) => (
                    <li key={student.id}>
                        {student.name} - {student.email}
                        <button onClick={() => handleDelete(student.id)}>Delete</button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default StudentList;
