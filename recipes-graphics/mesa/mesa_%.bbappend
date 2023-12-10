PACKAGECONFIG = " \
    ${@bb.utils.filter('DISTRO_FEATURES', 'wayland x11', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'opengl egl gles gbm', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11 opengl', 'dri3', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11 vulkan', 'dri3', '', d)} \
    \
    gallium \
    lima \
"
